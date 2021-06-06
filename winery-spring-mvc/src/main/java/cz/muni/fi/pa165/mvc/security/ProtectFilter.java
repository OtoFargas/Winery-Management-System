package cz.muni.fi.pa165.mvc.security;

import cz.muni.fi.pa165.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Protects administrative part of application.
 *
 * @author Oto Fargas
 */
@WebFilter(urlPatterns = {"/"})
public class ProtectFilter implements Filter {

    private final static Logger log = LoggerFactory.getLogger(ProtectFilter.class);

    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sRequest;
        HttpServletResponse response = (HttpServletResponse) sResponse;

        HttpSession session = request.getSession();
        UserDTO authUser = (UserDTO) session.getAttribute("authenticatedUser");

        if (authUser == null) {
            String redirectURL = request.getContextPath() + "/auth/login";
            response.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
            response.setHeader("Location", redirectURL);

            log.info("Redirecting to login from {}", request.getRequestURI());
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("Running in {}", filterConfig.getServletContext().getServerInfo());
    }
}
