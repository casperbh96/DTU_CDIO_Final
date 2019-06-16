

function SetupUsersLog(){
    $(function () {
        $.ajax({
            type: 'GET',
            url: '/rest/DataType2',
            succes: function (data) {
                console.log('succes', data);
            }
        });
    })
}

function handleShape( e ) {
    $( ".shape" )
        .removeClass( "circle pill square rectangle" )
        .addClass( $( e.target ).val() );
};
function handleToggle( e ) {
    var target = $( e.target );

    if ( target.is( ".brand-toggle" ) ) {
        var checked = target.is( ":checked" ),
            value = $( "[name='brand']" )
                .filter( ":checked" )
                .attr( "data-" + target[ 0 ].id )
        $( ".shape" ).css( target[ 0 ].id, checked ? value : "" );
    } else {
        $( ".shape" ).toggleClass( target[ 0 ].id, target.is( ":checked") );
    }
}
function updateBrand() {
    handleShape( { target: $( "[name='shape']:checked" ) } );
    $( ".toggle:checked" ).each( function() {
        handleToggle( { target: $( this ) } );
    } );
}


$(document).ready(function () {

    $(".SelectionRangeOptions").selectmenu();
    $(".SelectionRangeSlider").slider();
    $( ".radioContent_container" ).controlgroup( {
        direction: "vertical"
    } );
    $( ".radioContent_container" ).on( "change", handleToggle );
    SetupUsersLog()

});