
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
	
	var activityListOptions = document.querySelectorAll("#activityList option");
	for (var i = 0; i < activityListOptions.length; i++) {
		if (activityListOptions[i].getAttribute("value") == activityListID) {
			activityListOptions[i].setAttribute("selected","");
		}
	}
	
	var locationOptions = document.querySelectorAll("#locationSelect option");
	for (var i = 0; i < locationOptions.length; i++) {
		if (locationOptions[i].getAttribute("value") == locationID) {
			locationOptions[i].setAttribute("selected","");
		}
	}
		
});

function viewSingleWorkout(id) {
	var tdWorkout = document.querySelector(".tdWorkout");
	window.location.replace('/workout/' + id);
}	

function addRow() {
	const table = document.querySelector(".tableNoBorder tbody");
	const row = document.createElement("tr");
	const id = document.createElement("td");
	const idInput = document.createElement("input");
	var lastSetNum = document.querySelector(".tableNoBorder tr:last-child td:first-child input");
	var idValue;
	if (lastSetNum == null) {
		idValue = 0;
	}else {
		idValue = lastSetNum.value;
	}
	idInput.setAttribute("class","setCell");
	idInput.setAttribute("name", "sets["+idValue+"].setOrder");
	idInput.setAttribute("value", parseInt(idValue)+1);
	idInput.setAttribute("readonly","");
	id.appendChild(idInput);
	const reps = document.createElement("td");
	const repsInput = document.createElement("input");
	repsInput.setAttribute("class","setCell");
	repsInput.setAttribute("name","sets["+idValue+"].reps");
	repsInput.setAttribute("value",0);
	reps.appendChild(repsInput);
	const weight = document.createElement("td");
	const weightInput = document.createElement("input");
	weightInput.setAttribute("class","setCell");
	weightInput.setAttribute("name","sets["+idValue+"].weight");
	weightInput.setAttribute("value",0);
	weight.appendChild(weightInput);
	const x = document.createElement("td");
	const xButton = document.createElement("button");
	xButton.setAttribute("type", "button");
	xButton.setAttribute("onclick", "this.parentElement.parentElement.remove()");
	xButton.innerText="X";
	x.appendChild(xButton);
	row.appendChild(id);
	row.appendChild(reps);
	row.appendChild(weight);
	row.appendChild(x);
	table.append(row);
}
/*
function deleteRow() {
	//const row = $(this).parent().parent();
	//const row = this.parentElement.parentElement;
	console.log(this);
	console.log(row);
	row.style.backgroundColor = "red";
	row.remove();
}*/

