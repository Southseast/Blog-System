<%@ page import="java.util.Calendar" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<footer id="footer" class="footer">
    <div class="footer-inner">
        <% int now = Calendar.getInstance().get(Calendar.YEAR);
            request.setAttribute("now", now); %>


        <div class="copyright">© ${obj.config.createY} — <span>${now}</span>
            <span class="with-love" id="animate">
                            <i class="fa fa-user"></i>
                        </span>
            <span class="author">${obj.host.nickname}</span>
        </div>
        <div class="powered-by">
            <span style="display: inline;">有${obj.trafficVolume}只猫来偷过鱼 </span>
        </div>
    </div>
</footer>