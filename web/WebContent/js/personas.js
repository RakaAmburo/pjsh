var theQueue = $({}); // jQuery on an empty object - a perfect queue holder
var queueName = "QU";

$(document).ready(function() {
	
	timeOutStart ({
        message: "La session esta a punto de expirar",
        redirUrl: "http://localhost:8080/web/j_spring_security_logout",
        logoutUrl: "http://localhost:8080/web/j_spring_security_logout",
        warnAfter: 240000,
        redirAfter: 500000
    });
	
	generateUniqueId();

	Correct();

	ActInputEvents();
	
	$('.boot-tag-class').tagsinput({
		  tagClass: 'label label-default'
		});

	$(".btnDel").bind("click", Delete);
	$(".btnSave").bind("click", Save);
	$(".addUp").bind("click", function() {
		Add("up", $(this));
	});
	$(".addDown").bind("click", function() {
		Add("down", $(this));
	});
	$(".mvUp").bind("click", function() {
		Move("up", $(this));
	});
	$(".mvDown").bind("click", function() {
		Move("down", $(this));
	});
	$(".openMore").bind("click", OpenMore);
	
	//country select poputate
	$('#tabla input.pais').each(function() {

		setAutocomplete($(this));

	});

	$("#tabla").sortable({
		stop : function(event, ui) {
			Correct("noSave");
			CorrectOrder();
			$("#orderChanged").val("true");
		}
	});

	//Order
	setInterval(function() {
		var events = function() {
			$("#orderChanged").val("false");
			console.log("se updatea el orden");
		};
		if ($("#orderChanged").val() == "true") {
			if (!persistOrder(events)) {
				return false;
			}		
		}
		
	}, 2 * 1000);
	
	//data saving
	setInterval(function() {
		theQueue.dequeue(queueName);
	}, 4 * 1000);

});

function encolar(operation) {

	theQueue.queue(queueName, function(next) {

		setTimeout(
				  function() 
				  {
					  
					  operation(next);
					  
				  }, 150);
	
	});
	
	//mock
	$("#queue").val(theQueue.queue(queueName).length);
   
}

//Open more option for more imputs
function OpenMore(){
	var div = $(this).closest("#line").find(".moreOptions");
	var tagList = $(this).closest("#line").find("#perLangSelect");
	if (!div.hasClass('ui-dialog-content')){
		div.dialog(
				{
					appendTo: div.closest("td"),
					title : "mas opciones",
					autoOpen: false,
					resizable : false,
					modal : true,
					width: 600,
			        height: 500,
			        position: { my: 'top', at: 'top+80' },
					
					show: {
				        effect: "blind",
				        duration: 200
				      },
					hide: {
				        effect: "explode",
				        duration: 200
				      }
//					buttons : {
//						"Ok" : function() {
//							$(this).dialog({ hide: { effect: "explode", duration: 1000 } });
//						}
//					}
				});
		
	
		tagList.chosen({
		    disable_search_threshold: 10,
		    no_results_text: $("#langNotFoundMessage").val(),
		    width: "100%"
		  });
		
		//setChosenList(tagList);
	}
		
	tagList.chosen().trigger("chosen:updated");
	div.find("input.default").css("width", "153px");//corrijo el width del input chossen que por default es chico
	div.dialog( "open" );
}

//Actions, modify button on click move tr etc
function Delete() {
	var row = $(this).closest("#line");
	var fields = $(this).closest("#line").find("input").serialize();
	
	// no funciona cuenta mal las rows no se por que
	if ($('#tabla tr').length == 1){
		 Add("down", $(this));
	}
	
	row.remove();
	Correct();
	CorrectOrder();
	var events = function() {
		
	};
	var operation = function(next) {
		sendAjax(fields, "deletePerson", events, next);
	};
	encolar(operation);
	$("#orderChanged").val("true");
}
function Save() {

	var button = $(this);
	var id = $(this).closest("#line").find("input.id");
	var personaExt_id = $(this).closest("#line").find("input.personaExt_id");
	var idStart = id.val();
	var newRow = false;
	if (idStart.length == 0) {
		newRow = true;
	}
	
	var fields = $(this).closest("#line").find("input, select");
	
	//var fieldsSerialized = fields.serialize();
	var events = function(data) {
        
		if (data.returnedId != null && data.returnedId.length != 0) {
			id.val(data.returnedId);
			personaExt_id.val(data.returnedId);
			/*if (newRow){
				fields.prop('disabled', false);
			}*/
		}
	};
	button.prop('disabled', true);
	var operation = function(next) {
		sendAjax(fields.serialize(), "savePerson", events, next);
	};

	encolar(operation);
	if (newRow) {
		//fields.prop('disabled', true);
		$("#orderChanged").val("true");
	}
	Correct("noSave");

}

// Not necesary, fields are objects
function findMissingId(fields){
	
	var id = null;
	var uId = null;
	fields.each(function(){
		if($(this).attr('name') == "id"){
			id = $(this).val();
		}
		if($(this).attr('name') == "uniqueId"){
			uId = $(this).val();
		}
	});
	
	if (id == ""){
		var newId = $("#"+uId).closest("#line").find("input.id").val();
		fields.each(function(){
			if($(this).attr('name') == "id"){
				$(this).val(newId);
			}
		});
	}
	
	return fields.serialize();
	
}


function Add(where, that) {
	var row = that.closest("#line");
	var newRow = row.clone(true, true);
	
	var MoreOpt = newRow.find(".moreOptions");
	var cleanMoreOpt = MoreOpt.clone();
	MoreOpt.before(cleanMoreOpt).remove();
	var newSelect = row.find("#perLangSelect").clone();
	newSelect.children().removeAttr("selected");
	cleanMoreOpt.removeClass('ui-dialog-content');
	newRow.find(".chosentd").html(newSelect);
	var tagSel = row.find(".boot-tag-class").clone();
	tagSel.find('option').remove().end();
	newRow.find(".boot-tag").html(tagSel);
	tagSel.tagsinput({tagClass: 'label label-default'});
	
	newRow.find("input").each(function() {
		$(this).val('');
	});
	newRow.find("input[type=text]").off("keyup").keyup(function() {
		newRow.find("button.btnSave").prop('disabled', false);
	});
	var paisInput = newRow.find("input.pais");
	var cleanPaisInput = paisInput.clone();
	paisInput.before(cleanPaisInput).remove();
	cleanPaisInput.keyup(function() {
		newRow.find("button.btnSave").prop('disabled', false);
	});
	setAutocomplete(cleanPaisInput);
	switch (where) {
	case "up":
		row.before(newRow);
		break;
	case "down":
		row.after(newRow);
		break;
	}
	CorrectOrder();
}
function Move(where, that) {
	var row = that.closest("#line");
	var newRow = row.clone(true, true);
	newRow.find("input[type=text]").off("keyup").keyup(function() {
		newRow.find("button.btnSave").prop('disabled', false);
	});
	var sibling = row.prev();
	switch (where) {
	case "up":
		sibling = row.prev();
		sibling.before(newRow);
		break;
	case "down":
		sibling = row.next();
		sibling.after(newRow);
		break;
	}

	row.remove();
	Correct();
	CorrectOrder();

	$("#orderChanged").val("true");
}

function persistOrder(events) {
	
	var breakIfNull = false;
	var orders = [];
	$('#tabla input.id').each(function() {
		orders.push($(this).val());
		if ($(this).val() == null || $(this).val() == "" && $('#tabla input.id').length != 1) {
			breakIfNull = true;
		}
	});
	if (breakIfNull) {
		return false;
	}
	//console.log(orders);
	sendAjax({
		jsonOrder : orders
	}, "saveOrder", events);

	return true;
}

//No se usa se carga en load
function setChosenList(tagList){
	$.ajax({
		url : "languages",
		/*data : {
			countryLike : "A"
		},*/
		success : function(data) {
			
			$.map(data.langMap, function(value, key) {
				
				tagList.append($("<option></option>").val(key).html(value));
				
			});
			
			tagList.chosen().trigger("chosen:updated");
		}
	});
}


function setAutocomplete(that) {

	that.autocomplete({
		source : function(request, response) {
			$.ajax({//se podria mejorar para que lame una sola vez (abajo implementar solo seria )
				/*var term = request.term;
		        if ( term in cache ) {
		          response( cache[ term ] );
		          return;
		        }
		 
		        $.getJSON( "search.php", request, function( data, status, xhr ) {
		          cache[ term ] = data;
		          response( data );
		        });*/
				url : "countries",
				data : {
					countryLike : request.term
				},
				success : function(data) {
					response($.map(data.ctryMap, function(key, value) {
						return {
							id : value,
							value : key
						};
					}));
				}
			});
		},
		minLength : 2,
		///delay: 500, probar
		select : function(event, ui) {
			that.closest("#line").find("input.paisId").val(ui.item.id);
		}
	});

}

//Correct the state of buttons enabled/desabled etc
function Correct(noSave) {
	var rowCount = $('#tabla tr').length;
	
	$('#tabla tr').each(function() {
		
		if(noSave  == null){
			$(this).find("button.btnSave").prop('disabled', true);
		}
		
		if (rowCount == 1 && $(this).find("input[name='nombre']").val() == ""){
        	$(this).find("button.mvUp").prop('disabled', true);
			$(this).find("button.mvDown").prop('disabled', true);
			$(this).find("button.addUp").prop('disabled', true);
			$(this).find("button.addDown").prop('disabled', true);
			$(this).find("button.btnDel").prop('disabled', true);
        }else if (rowCount == 1){
        	$(this).find("button.mvUp").prop('disabled', true);
			$(this).find("button.mvDown").prop('disabled', true);
        } else if ($(this).is(':first-child')) {
			$(this).find("button.mvUp").prop('disabled', true);
			$(this).find("button.mvDown").prop('disabled', false);
		} else if ($(this).is(':last-child')) {
			$(this).find("button.mvDown").prop('disabled', true);
			$(this).find("button.mvUp").prop('disabled', false);
		} else {
			$(this).find("button.mvUp").prop('disabled', false);
			$(this).find("button.mvDown").prop('disabled', false);

		}
		
		if (rowCount == 1 && $(this).find("input[name='nombre']").val() != ""){
			$(this).find("button.btnDel").prop('disabled', false);
			$(this).find("button.addUp").prop('disabled', false);
			$(this).find("button.addDown").prop('disabled', false);
		}
	});
}

function CorrectOrder() {
	var index = 1;
	$('#tabla td.orden').each(function() {
		$(this).html(index);
		index++;
	});
}

//Detect when something changes
function ActInputEvents() {
	$('#tabla tr').each(function() {

		var that = $(this);
		$(this).find("input[type=text]").keyup(function() {
			that.find("button.btnSave").prop('disabled', false);
		});
		$(this).find("select").change(function() {
			that.find("button.btnSave").prop('disabled', false);
		});

	});
}

function sendAjax(fields, action, events, next) {
	
	//bug mocking
	if($("#noInternet").is(':checked')){
		actionAux = action;
		action = "noroute";
		
	}

	console.log(fields);
	$.ajax({
		cache : false,
		url : action,
		async : true,
		type : "POST",
		data : fields,
		error : function(data) {
			    $("#errorMsg").css("visibility", "visible");
				var operation = function(next) {
					sendAjax(fields, actionAux, events, next);
				};
				theQueue.queue(queueName).unshift( operation );
	
			},
		success : function(data) {
			
			if (data.status == false) {
				custom_alert("Falla de comunicacion, intente mas tarde");
			} else {
				$("#errorMsg").css("visibility", "hidden");
				events(data);
				if (next) {

					$("#queue").val(theQueue.queue(queueName).length);

					next();
				}
			}

		}
	});
}

function generateUniqueId(){
	 	$(".uniqueId").each(function() {
	    $(this).uniqueId();
	});
	
}

function custom_alert(output_msg, title_msg) {
	if (!title_msg)
		title_msg = 'Alert';

	if (!output_msg)
		output_msg = 'No Message to Display.';

	$("<div></div>").html(output_msg).dialog(
			{
				title : title_msg,
				resizable : false,
				modal : true,
				open : function(event, ui) {
					//hide close button.
					$(this).parent().children().children(
							'.ui-dialog-titlebar-close').hide();
				},
				buttons : {
					"Ok" : function() {
						$(this).dialog("close");
					}
				}
			});
}
