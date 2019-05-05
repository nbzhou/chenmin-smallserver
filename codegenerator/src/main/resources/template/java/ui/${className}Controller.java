<#assign className = table.className>
<#assign classNameLower = table.classNameLower><#if  '${subpackage}'=="">
package ${basepackage}.controller;
<#else>
package ${basepackage}.${subpackage}.controller;
</#if>

import ${basepackage}.${subpackage}.intf.clients.${className}Client;
import ${basepackage}.${subpackage}.intf.pojo.${className};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import com.zhonghuilv.aitravel.common.ui.controller.BasicUIController;

/**
 * Created by ${author}  on ${date}
 */
@RestController
@RequestMapping("/${table.mapping}")
@Api(value = "${className}Controller", description = "${table.tableRemark}")
public class ${className}Controller extends BasicUIController<${className}>{


	private ${className}Client ${classNameLower}Client;

    @Autowired
    public ${className}Controller(${className}Client ${classNameLower}Client){
        super(${classNameLower}Client);
        this.${classNameLower}Client = ${classNameLower}Client;
    }


}

