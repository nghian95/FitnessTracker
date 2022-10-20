$(document).ready(function() {
	var confirmElems = document.getElementsByClassName("confirm");
	var confirmIt = function(e) {
		if (!confirm('Are you sure?')) e.preventDefault();
	};
	for (var i = 0, j = confirmElems.length; i < j; i++) {
		confirmElems[i].addEventListener('click', confirmIt, false);
	}
});