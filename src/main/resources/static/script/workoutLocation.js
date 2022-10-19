$(document).ready(function() {
	var locationOptions = document.querySelectorAll("#locationSelect option");
	for (var i = 0; i < locationOptions.length; i++) {
		if (locationOptions[i].getAttribute("value") == locationID) {
			locationOptions[i].setAttribute("selected","");
		}
	}
		
});