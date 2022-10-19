$(document).ready(function() {
	var activityListOptions = document.querySelectorAll("#activityList option");
	for (var i = 0; i < activityListOptions.length; i++) {
		if (activityListOptions[i].getAttribute("value") == activityListID) {
			activityListOptions[i].setAttribute("selected","");
		}
	}	
});