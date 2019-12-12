(function ($) {
	$("#menu-toggle").click(function(e) {
	      e.preventDefault();
	      $("#wrapper").toggleClass("toggled");
	});
	
	// Modal to confirm deletion element in list : modal is managed by Bootstrap
	  $('#deletionModal').on('show.bs.modal', function (event) {
		  // element deletion link that triggered the modal
		  var elDelLink = $(event.relatedTarget) 
		  // Extract info from data-link attributes
		  var url = elDelLink.data('link') 
		  var name = elDelLink.data('name') 
		  var label = elDelLink.data('label') 
		  // confirmation button of modal is set with link value extract from element deletion link
		  $(this).find("#melementLabel").html(label)
		  $(this).find("#melementName").html(name)
		  $(this).find("#mdeletionLink").attr("href", url)
		});
	
})(jQuery);
