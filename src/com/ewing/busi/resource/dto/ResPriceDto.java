package com.ewing.busi.resource.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ewing.busi.resource.model.WebResourcePrice;

/**
 * 
 * 
 * @author tanson lam
 * @creation 2016年1月14日
 */
public class ResPriceDto {
	private WebResourcePrice price;

	private List<String> specIds;

	public WebResourcePrice getPrice() {
		return price;
	}

	public void setPrice(WebResourcePrice price) {
		this.price = price;
	}

	public List<String> getSpecIds() {
		return specIds;
	}

	public void setSpecIds(List<String> specIds) {
		this.specIds = specIds;
	}

	public Boolean isSameSpecIds(ResPriceDto newOne) {
		if (this.specIds == null || newOne.specIds == null)
			return false;
		if (this.specIds.size() != newOne.specIds.size())
			return false;
		for (String oldSpecId : specIds) {
			boolean hasSame = false;
			for (String newSpecId : newOne.specIds) {
				if (oldSpecId.equals(newSpecId)) {
					hasSame = true;
				}
			}
			if (!hasSame)
				return false;
		}
		return true;
	}

	public static List<ResPriceDto> toPriceDto(List<WebResourcePrice> priceList) {
		List<ResPriceDto> dtoList = new ArrayList<ResPriceDto>();
		for (WebResourcePrice price : priceList) {
			ResPriceDto dto = new ResPriceDto();
			dto.setPrice(price);
			dto.setSpecIds(Arrays.asList(price.getSpecIds().split(",")));
			dtoList.add(dto);
		}
		return dtoList;
	}

}
