import javax.servlet.annotation.WebFilter;
import java.io.IOException;
//@WebFilter("/*")
public class CharsetFilter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {
       req.setCharacterEncoding("utf-8");
        chain.doFilter(req, resp);
    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {

    }

}
