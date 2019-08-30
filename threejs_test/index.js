
function timer(dur, worker){
	  var timeDead = setTimeout("timer("+dur+","+worker+");", dur);
	  worker();
}

var renderer, scene, camera;

var earth;

function init() {
    renderer = new THREE.WebGLRenderer({
        canvas: document.getElementById('mainCanvas')
    });
    renderer.setClearColor(0x000066);


     scene = new THREE.Scene();

    var light = new THREE.AmbientLight(0xFFFFFF);
    scene.add(light);

    camera = new THREE.PerspectiveCamera(45, 4 / 3, 1, 1000);
    camera.position.set(0, 0, 10);
    scene.add(camera);

    var texture = new THREE.TextureLoader(
        // 'img/pic.png',{}, function(){
        //     renderer.render(scene, camera);
        // }
    ).load('img/pic.png');

    var cube = new THREE.Mesh(
        new THREE.CubeGeometry(1, 1, 1),
        new THREE.MeshLambertMaterial({
            // color: 0xff0000,
            map: texture
        }));
    //scene.add(cube);

    earth = new THREE.Mesh(
        new THREE.SphereGeometry(3, 32, 32,
                                 // Math.PI/6, Math.PI/2,
                                 // Math.PI/6, Math.PI/2
                                 0, Math.PI*2,
                                 0, Math.PI*2
                                ),//, 180, 1, 80),
        new THREE.MeshLambertMaterial({
            // color: 0xffff00,
            // wireframe: false,
            // emissive: 0xff0000,
            map: texture
        }));
    // 半径为1, 经度切片数为10, 纬度切片宽度为10,
    // 经度开始的弧度0，经度跨过的弧度180
    //纬度开始的弧度1, 纬度跨过的弧度80
    scene.add(earth);
    earth.rotation.y += Math.PI;

    renderer.render(scene, camera); 
}


var times = 400;

function animate() {

    requestAnimationFrame( animate );

    earth.rotation.y += 2*Math.PI/times;
    // mesh.rotation.y += 0.02;
    
    renderer.render( scene, camera );

}

init();
animate();

//renderer.render( scene, camera );
