1.数据模型
     分组 1 --> n 分类    
       (web_group --> web_category )
     分类 1 --> n 分类与资源的关系 1 --> 1 资源
       (web_catagory --> web_rel_resource --> web_resource)
     资源 1 --> n 资源属性      
       (web_resource --> web_resource_attr)
2.显示动态数据方式
     在页面使用多个group_key来获取web_catagory里面的数据并且进行显示:
  a.导航分类, 以标题方式出现，如导航栏、区域分类等等方式显示；二级链接指定页面； 
  b.分类链接, 以区域、产品列表等等方式显示；
          产品列表需要设置产品列表模板；
  c.单个资源显示，当分类和资源建立关系的时候需要指定模板页面，用来显示该资源的相关信息。
     