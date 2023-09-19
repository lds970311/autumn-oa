package com.autumn.filter;


import com.alibaba.fastjson.JSON;
import com.autumn.common.JwtHelper;
import com.autumn.result.Result;
import com.autumn.result.ResultCodeEnum;
import com.autumn.util.ResponseUtil;


import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private StringRedisTemplate stringRedisTemplate;

    public TokenAuthenticationFilter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("uri:" + request.getRequestURI());
        //如果是登录接口，直接放行
        if ("/admin/system/index/login".equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (null != authentication) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            ResponseUtil.out(response, Result.build(null, ResultCodeEnum.PERMISSION));
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // token置于header里
        String token = request.getHeader("token");
        List<SimpleGrantedAuthority> authorityList = null;
        logger.info("token:" + token);
        if (!StringUtils.isEmpty(token)) {
            String useruame = JwtHelper.getUsername(token);
            logger.info("username:" + useruame);
            //从redis中获取权限数据
            ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
            String authorStr = valueOperations.get(useruame);
            if (!StringUtils.isEmpty(authorStr)) {
                List<Map> mapList = JSON.parseArray(authorStr, Map.class);
                authorityList = new ArrayList<>();
                for (Map map : mapList) {
                    String authority = (String) map.get("authority");
                    authorityList.add(new SimpleGrantedAuthority(authority));
                }
                return new UsernamePasswordAuthenticationToken(useruame, null, authorityList);
            } else {
                return new UsernamePasswordAuthenticationToken(useruame, null, Collections.emptyList());
            }

        }
        return null;

    }
}
