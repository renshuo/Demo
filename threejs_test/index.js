function init() {
    var renderer = new THREE.WebGLRenderer({
        canvas: document.getElementById('mainCanvas')
    });
    renderer.setClearColor(0x000066);

    var scene = new THREE.Scene();

    var camera = new THREE.PerspectiveCamera(45, 4 / 3, 1, 1000);
    camera.position.set(0, 0, 5);
    scene.add(camera);

    var texture = THREE.TextureLoader(
        'img/sprite1.png',{}, function(xhr){
            renderer.render(scene, camera);
        });

    var cube = new THREE.Mesh(
        new THREE.CubeGeometry(1, 1, 1),
        new THREE.MeshLambertMaterial({
            // color: 0xff0000,
            // wireframe:
            map: texture
        }));
    scene.add(cube);

    var earth = new THREE.Mesh(
        new THREE.SphereGeometry(1, 10, 10,
                                 // Math.PI/6, Math.PI/2,
                                 // Math.PI/6, Math.PI/2
                                 0, Math.PI,
                                 0, Math.PI
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
    //scene.add(earth);

    renderer.render(scene, camera); 
}
