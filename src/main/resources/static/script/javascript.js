
$(document).ready(function() {
    $('textarea').on('keyup keypress', function() {
        $(this).height(0);
        $(this).height(this.scrollHeight);
    });
	var txtarea = document.querySelector(".textarea");
	if (txtarea != null) {
	$(txtarea).height(txtarea.scrollHeight);
	}

//	$(trWorkout).on('onclick'), function() {
//		window.location.replace('/workout');	
//	};

	var confirmElems = document.getElementsByClassName("confirm");
	var confirmIt = function(e) {
		if (!confirm('Are you sure?')) e.preventDefault();
	};
	for (var i = 0, j = confirmElems.length; i < j; i++) {
		confirmElems[i].addEventListener('click', confirmIt, false);
	}
});

function viewSingleWorkout(id) {
	var tdWorkout = document.querySelector(".tdWorkout");
	window.location.replace('/workout/' + id);
}	

