<#assign className = table.className>
<#assign classNameLower=table.classNameLower>
package ${basepackage}.${subpackage}.clients;

import ${basepackage}.${subpackage}.intf.${constantName};
import com.zhonghuilv.aitravel.common.intf.BasicClient;
import ${basepackage}.${subpackage}.intf.pojo.${className};
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by ${author}  on ${date}
 */
@FeignClient(value = ${constantName}.SERVICE_NAME)
@RequestMapping("/${table.mapping}")
public interface ${className}Client extends BasicClient<${className}> {

}

