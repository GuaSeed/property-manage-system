package edu.xihua.project.pms.config;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import lombok.extern.java.Log;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

@Log
@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class})})
@Component
public class AutoDateInterceptor implements Interceptor {
    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public String getModifiedName() {
        return modifiedName;
    }

    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
    }

    private String createdName;
    private String modifiedName;

    @Override
    public Object intercept(Invocation invocation) throws Exception {
        try {
            Object[] args = invocation.getArgs();
            MappedStatement mappedStatement = (MappedStatement) args[0];
            // 参数
            Object params = args[1];
            // 插入
            if (mappedStatement.getSqlCommandType() == SqlCommandType.INSERT) {
                Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
                // 填充createdName属性
                insertField(params, createdName, date);
            } else if (mappedStatement.getSqlCommandType() == SqlCommandType.UPDATE) {
                MapperMethod.ParamMap<?> paramMap = (MapperMethod.ParamMap<?>) params;
                // 实体类
                Object et = paramMap.get(Constants.ENTITY);
                Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
                // 更新
                insertField(et, modifiedName, date);
            }
            return invocation.proceed();
        } catch (Exception e) {
            return invocation.proceed();
        }
    }

    private void insertField(Object et, String createdName, Date now) {
        Field[] declaredFields = et.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if (declaredField.getName().equals(createdName)) {
                try {
                    declaredField.set(et, now);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String createdName = (String) properties.get("createdName");
        String modifiedName = (String) properties.get("modifiedName");
        this.createdName = createdName;
        this.modifiedName = modifiedName;
    }
}
