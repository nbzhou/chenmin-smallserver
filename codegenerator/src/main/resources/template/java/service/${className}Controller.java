<#assign className = table.className>
<#assign classNameLower = table.classNameLower><#if  '${subpackage}'=="">
package ${basepackage}.controller;
<#else>
package ${basepackage}.${subpackage}.controller;
</#if>

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import ${basepackage}.common.service.controller.BasicController;
import ${basepackage}.${subpackage}.intf.pojo.${className};
import ${basepackage}.${subpackage}.intf.clients.${className}Client;
import ${basepackage}.${subpackage}.service.mapper.${className}Mapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ${author}  on ${date}
 */
@RestController
@RequestMapping("/${table.mapping}")
@Api(value = "${className}Controller", description = "${table.tableRemark}")
public class ${className}Controller extends BasicController<${className}> implements ${className}Client{

	private ${className}Mapper ${classNameLower}Mapper;

    @Autowired
    public ${className}Controller(${className}Mapper ${classNameLower}Mapper) {
        super(${classNameLower}Mapper);
        this.${classNameLower}Mapper =${classNameLower}Mapper;
    }


}

