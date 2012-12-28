$('.add-item').click(function () {
	$('.btm-border').toggle();
	$('.black-add').slideToggle(250);
});

$('.time-set').timepicker({ 'forceRoundTime': true });