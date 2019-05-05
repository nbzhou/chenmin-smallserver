<#assign className = table.className>
<#assign classNameLower = table.classNameLower>

package ${basepackage}.${subpackage}.intf.pojo;

import ${basepackage}.common.pojo.po.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

/**
 * Created by ${author}  on ${date}
 */
@ApiModel("${table.tableRemark}")
@Table(name = "${table.sqlName}")
@Data
public class ${className} extends MainPO{
	<@generateFields/>
}

<#macro generateFields>

	<#list table.columns as column>
		<#if !column.pk && !column.fk >
	<#if !column.baseField>
    ${column.apiModelProperty}
    private ${column.javaType} ${column.columnNameLower};

    </#if>
    <#elseif column.pk>
    @Id
	@OrderBy("desc")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ${column.javaType} ${column.columnNameLower};

		</#if>
	</#list>
</#macro>
