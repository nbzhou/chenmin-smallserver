<#assign className=table.className>
package ${basepackage}.${subpackage}.service.mapper;

import ${basepackage}.${subpackage}.intf.pojo.${className};
import com.zhonghuilv.aitravel.common.service.mapper.CommonMapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ${className}Mapper extends CommonMapper<${className}> {

}