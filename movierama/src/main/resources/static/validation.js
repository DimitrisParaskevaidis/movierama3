function validateForm() {
	let title = document.forms["insertionForm"]["title"].value;
	let description = document.forms["insertionForm"]["description"].value;
	let publicationdate = document.forms["insertionForm"]["publicationdate"].value;

	if ((title == "") || (description == "") || (publicationdate == "")) {
		alert("All fields must be filled out");
		event.preventDefault();
		return false;
	}

	return true;
}