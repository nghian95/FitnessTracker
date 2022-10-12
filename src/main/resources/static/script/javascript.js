
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

function addRow() {
	const table = document.querySelector(".tableNoBorder tbody");
	const row = document.createElement("tr");
	
	const id = document.createElement("td");
	const idInput = document.createElement("input");
	const lastSetNum = document.querySelector(".tableNoBorder tr:last-child td:first-child input");
	const idValue = lastSetNum.value;
	idInput.setAttribute("class","setCell");
	idInput.setAttribute("value", parseInt(idValue)+1);
	idInput.setAttribute("readonly","");
	id.appendChild(idInput);
	const reps = document.createElement("td");
	const repsInput = document.createElement("input");
	repsInput.setAttribute("class","setCell");
	repsInput.setAttribute("th:name","sets["+idValue+"].reps");
	reps.appendChild(repsInput);
	const weight = document.createElement("td");
	const weightInput = document.createElement("input");
	weightInput.setAttribute("class","setCell");
	weightInput.setAttribute("th:name","sets["+idValue+"].weight");
	weight.appendChild(weightInput);
	row.appendChild(id);
	row.appendChild(reps);
	row.appendChild(weight);
	table.append(row);
}

