package list.buku.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

public class CsrfHeaderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest req,
            HttpServletResponse resp,
            FilterChain filter
    ) throws ServletException, IOException {
        CsrfToken csrf = (CsrfToken) req.getAttribute(CsrfToken.class.getName());

		if(csrf != null) {
			Cookie cookie = WebUtils.getCookie(req, "XSRF-TOKEN");
			String token = csrf.getToken();

			if(cookie == null || token != null && 
					!token.equals(cookie.getValue())) {
				cookie = new Cookie("XSRF-TOKEN", token);
				cookie.setPath("/");
				resp.addCookie(cookie);
			}
		}

		filter.doFilter(req, resp);
    }
}