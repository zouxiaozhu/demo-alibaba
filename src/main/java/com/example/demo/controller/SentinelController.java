package com.example.demo.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanglong
 * @date 2020-08-31
 * package com.example.demo.controller
 * <p>
 * no coding, no bug
 */
@RestController
@RequestMapping("sentinel")
public class SentinelController {

    @GetMapping("entry")
    public String entry() throws BlockException {
        Entry entry = null;
// 务必保证 finally 会被执行
        try {
            // 资源名可使用任意有业务语义的字符串，注意数目不能太多（超过 1K），超出几千请作为参数传入而不要直接作为资源名
            // EntryType 代表流量类型（inbound/outbound），其中系统规则只对 IN 类型的埋点生效
            entry = SphU.entry("自定义资源名" ,EntryType.IN, 1);
            // 被保护的业务逻辑
            // do something...
        } catch (BlockException ex) {
            throw ex;
            // 资源访问阻止，被限流或被降级
            // 进行相应的处理操作
        } catch (Exception ex) {
            // 若需要配置降级规则，需要通过这种方式记录业务异常
            Tracer.traceEntry(ex, entry);
            throw ex;
        } finally {
            // 务必保证 exit，务必保证每个 entry 与 exit 配对
            if (entry != null) {
                entry.exit();
            }
        }
        return "a";
    }
}
