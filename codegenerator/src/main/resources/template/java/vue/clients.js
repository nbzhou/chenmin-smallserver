<#assign className = table.className>
<#assign classNameLower = table.classNameLower>

const ${className} = prefix + "/${className}"

export const ${className}Client = {
        saveOrUpdate: data =>
    request({
      url: ${className} + '/save_or_update',
      method: 'get',
        data: data
    }),
    updateSelective: data =>
    request({
      url: ${className} + '/update_selective',
      method: 'post',
      data: data
    }),
    loadOne: data =>
    request({
      url: ${className} + '/load_one',
      method: 'post',
      data: data
    }),
        loadList: data =>
    request({
      url: ${className} + '/load_list',
      method: 'post',
      data: data
    }) ,
        loadPageInfo: data =>
    request({
      url: ${className} + '/load_page_info',
      method: 'post',
      data: data
    })

}


