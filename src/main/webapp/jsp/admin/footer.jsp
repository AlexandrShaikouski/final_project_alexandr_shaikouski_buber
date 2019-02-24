<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</div>
<footer class="footer">
    <div class="d-sm-flex justify-content-center justify-content-sm-between">
        <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">Copyright © 2017 <a
                href="https://www.bootstrapdash.com/" target="_blank">Bootstrap Dash</a>. All rights reserved.</span>
        <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hand-crafted & made with <i
                class="mdi mdi-heart text-danger"></i></span>
    </div>
</footer>

</div>
</div>
</div>
<script src="${pageContext.servletContext.contextPath}/vendors/js/vendor.bundle.base.js"></script>
<script src="${pageContext.servletContext.contextPath}/vendors/js/vendor.bundle.addons.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/off-canvas.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/dashboard.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/script.js"></script>

<c:if test="${message_user != null}">
<div id="myModal"  class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">x</button>
            </div>
            <h3 class="modal-title text-center">Message</h3>
            <div class="modal-body text-center">${message_user}</div>
            <div class="modal-footer"><button class="btn btn-default" type="button" data-dismiss="modal">Close</button></div>
        </div>
    </div>
</div>
<button id="error-gid" style="display: none" type="button" data-toggle="modal" data-target="#myModal"/>
<script>$('#error-gid').trigger('click');</script>
</c:if>
</body>
</html>