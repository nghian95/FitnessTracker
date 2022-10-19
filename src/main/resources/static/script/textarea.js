/**
 * 
 */
 
 $(document).ready(function() {
    $('textarea').on('keyup keypress', function() {
        $(this).height(0);
        $(this).height(this.scrollHeight);
    });
	var txtarea = document.querySelector(".textarea");
	if (txtarea != null) {
	$(txtarea).height(txtarea.scrollHeight);
	}
});