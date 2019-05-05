<#assign className = table.className>
<#assign classNameLower = table.classNameLower><#if  '${subpackage}'=="">
package ${basepackage}.controller;
<#else>
package ${basepackage}.${subpackage}.controller;
</#if>

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import ${basepackage}.common.ApiResult;
import ${basepackage}.common.pojo.dto.PageQuery;
import ${basepackage}.${subpackage}.intf.clients.${className}Client;
import ${basepackage}.${subpackage}.intf.pojo.${className};

import io.swagger.annotations.*;

import java.util.List;

/**
 * Created by ${author}  on ${date}
 */
@RestController
@RequestMapping("/${table.mapping}")
@Api(value = "${className}Controller", description = "${table.tableRemark}")
public class ${className}Controller  {

    
    private ${className}Client ${classNameLower}Client;

    @Autowired
    public ${className}Controller(${className}Client ${classNameLower}Client) {
        this.${classNameLower}Client = ${classNameLower}Client;
    }

}

