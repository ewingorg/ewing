package com.admin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.admin.dto.ResPriceDto;
import com.admin.dto.WebResourceSpecGroup;
import com.admin.model.WebResource;
import com.admin.model.WebResourcePrice;
import com.admin.model.WebResourceSpec;
import com.core.app.constant.IsEff;
import com.core.jdbc.BaseDao;

/**
 * 
 * 资源价格服务类
 * 
 * @author tanson lam
 * @creation 2015年12月15日
 */
@Repository("webResourcePriceService")
public class WebResourcePriceService {

    @Resource
    private BaseDao baseDao;
    @Resource
    private WebResourceService webResourceService;
    @Resource
    private WebResourceSpecService webResourceSpecService;

    /**
     * 查询配置资源价格
     * 
     * @param resourceId
     * @return
     */
    public List<ResPriceDto> getConfigurePrices(Integer resourceId) {
        List<WebResourcePrice> priceList = baseDao.find("resource_id=" + resourceId
                + " and iseff='" + IsEff.EFFECTIVE + "' order by rank", WebResourcePrice.class);
        List<ResPriceDto> configurePrices = ResPriceDto.toPriceDto(priceList);

        // 获取资源所有规格
        List<ResPriceDto> defaultPrices = generateDefaultPrices(resourceId);

        for (ResPriceDto defaultPrice : defaultPrices) {
            for (ResPriceDto configurePrice : configurePrices) {
                if (defaultPrice.isSameSpecIds(configurePrice)) {
                    try {
                        BeanUtils.copyProperties(defaultPrice, configurePrice);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        return defaultPrices;
    }

    private List<ResPriceDto> generateDefaultPrices(Integer resourceId) {
        List<WebResourceSpecGroup> specList = webResourceSpecService.getConfigureSpecs(resourceId);
        List<ResPriceDto> priceList = new ArrayList<ResPriceDto>();
        // 穷尽所有可以配置的规格价钱
        List<List> specIdList = new ArrayList<List>();
        for (WebResourceSpecGroup group : specList) {
            List<String> specs = new ArrayList<String>();
            for (WebResourceSpec spec : group.getChildParamlist()) {
                specs.add(spec.getId().toString());
            }
            specIdList.add(specs);
        }
        if (!specIdList.isEmpty()) {
            List<String> allResult = descartes(specIdList);
            for (String result : allResult) {
                ResPriceDto price = new ResPriceDto();
                List<String> specIds = Arrays.asList(result.split(","));
                price.setSpecIds(specIds);
                priceList.add(price);
            }
        }
        return priceList;

    }

    public static void main(String[] args) {
        List<List> specIdList = new ArrayList<List>();
        List<String> list1 = new ArrayList<String>();
        list1.add("a");
        list1.add("b");
        List<String> listb = new ArrayList<String>();
        listb.add("c");
        listb.add("d");
        specIdList.add(list1);
        specIdList.add(listb);
        List<String> result = descartes(specIdList);
        System.out.println(result);

    }

    @SuppressWarnings("rawtypes")
    public static List<String> descartes(List<List> strs) {
        int total = 1;
        for (int i = 0; i < strs.size(); i++) {
            int size = strs.get(i).size();
            total *= size;
        }
        String[] mysesult = new String[total];
        int now = 1;
        // 每个元素每次循环打印个数
        int itemLoopNum = 1;
        // 每个元素循环的总次数
        int loopPerItem = 1;
        for (int i = 0; i < strs.size(); i++) {
            List temp = strs.get(i);
            now = now * temp.size();
            // 目标数组的索引值
            int index = 0;
            int currentSize = temp.size();
            itemLoopNum = total / now;
            loopPerItem = total / (itemLoopNum * currentSize);
            int myindex = 0;
            for (int j = 0; j < temp.size(); j++) {

                // 每个元素循环的总次数
                for (int k = 0; k < loopPerItem; k++) {
                    if (myindex == temp.size())
                        myindex = 0;
                    // 每个元素每次循环打印个数
                    for (int m = 0; m < itemLoopNum; m++) {
                        mysesult[index] = (mysesult[index] == null ? "" : mysesult[index] + ",")
                                + ((String) temp.get(myindex));
                        index++;
                    }
                    myindex++;

                }
            }
        }
        return Arrays.asList(mysesult);
    }

    /**
     * 批量保存资源价格
     * 
     * @param resourceId
     * @param resPriceList
     * @throws Exception
     */
    public void savePriceList(Integer resourceId, List<WebResourcePrice> resPriceList)
            throws Exception {
        if (resourceId == null || resPriceList == null)
            return;
        WebResource webResource = webResourceService.findById(resourceId);
        if (webResource == null)
            throw new Exception("没有找到匹配的资源信息");

        List<ResPriceDto> oldPriceList = getConfigurePrices(resourceId);

        for (WebResourcePrice spec : resPriceList) {
            WebResourcePrice oldPrice = findByOldPrice(spec, oldPriceList);
            if (oldPrice != null) {
                oldPrice.setPrice(spec.getPrice());
                oldPrice.setStockNum(spec.getStockNum());
                oldPrice.setGiftScore(spec.getGiftScore());
                oldPrice.setRank(spec.getRank());
                oldPrice.setCost(spec.getCost());
                baseDao.update(oldPrice);
            } else {
                spec.setResourceId(resourceId);
                spec.setIseff(IsEff.EFFECTIVE);
                baseDao.save(spec);
            }
        }

        // 删除价格设置
        List<WebResourcePrice> removeList = new ArrayList<WebResourcePrice>();
        List<ResPriceDto> newPriceDtoList = ResPriceDto.toPriceDto(resPriceList);
        for (ResPriceDto oldPrice : oldPriceList) {
            boolean exist = false;
            for (ResPriceDto newPrice : newPriceDtoList) {
                if (newPrice.isSameSpecIds(oldPrice)) {
                    exist = true;
                    break;
                }
            }
            if (!exist && oldPrice.getPrice() != null)
                removeList.add(oldPrice.getPrice());
        }

        for (WebResourcePrice price : removeList) {
            baseDao.delete(price);
        }
    }

    /**
     * 删除失效的价格设置
     * 
     * @param resourceId
     */
    public void removePriceForChangeSpec(Integer resourceId) {
        // 获取资源所有规格
        List<WebResourcePrice> priceList = baseDao.find("resource_id=" + resourceId
                + " and iseff='" + IsEff.EFFECTIVE + "' order by rank", WebResourcePrice.class);
        List<ResPriceDto> defaultPrices = generateDefaultPrices(resourceId);
        List<ResPriceDto> configurePrices = ResPriceDto.toPriceDto(priceList);
        List<WebResourcePrice> removePriceList = new ArrayList<WebResourcePrice>();

        for (ResPriceDto configurePrice : configurePrices) {
            boolean exist = false;
            for (ResPriceDto defaultPrice : defaultPrices) {
                if (defaultPrice.isSameSpecIds(configurePrice)) {
                    exist = true;
                    break;
                }
            }
            if (!exist)
                removePriceList.add(configurePrice.getPrice());
        }

        for (WebResourcePrice price : removePriceList) {
            baseDao.delete(price);
        }
    }

    /**
     * 根据名称查找规格信息
     * 
     * @param resourceId
     * @param specName
     * @return
     */
    public WebResourcePrice findByOldPrice(WebResourcePrice newSpec, List<ResPriceDto> oldPriceList) {
        ResPriceDto newSpecDto = new ResPriceDto();
        newSpecDto.setSpecIds(Arrays.asList(newSpec.getSpecIds().split(",")));

        for (ResPriceDto price : oldPriceList) {
            if (price.isSameSpecIds(newSpecDto)) {
                return price.getPrice();
            }
        }
        return null;
    }
}
