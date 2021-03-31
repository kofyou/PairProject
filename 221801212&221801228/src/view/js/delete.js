
    function ondelete(ob){
    var child = ob.parentElement.parentElement.parentElement;
    var parent = child.parentElement;
    parent.removeChild(child);
}
