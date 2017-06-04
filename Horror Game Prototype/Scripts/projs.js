#pragma strict
 
// Change renderer's texture each changeInterval
// seconds from the texture array defined in the inspector.
var textures : Texture[];
var changeInterval = 0.33;
var proj : Projector;  // you can drag the projector onto this in the editor, then get rid of the Start() function if you want.
 
function Start() {
     proj = GetComponent (Projector);
}
 
function Update() {
 
    if( textures.length == 0 ) // nothing if no textures
        return;
 
    // we want this texture index now
    var index : int = Time.time / changeInterval;
    // take a modulo with size so that animation repeats
    index = index % textures.length;
    // assign it
    proj.material.mainTexture = textures[index];
}