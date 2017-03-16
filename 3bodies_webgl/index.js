

var scene, renderer, camera;


var cube, bodys;

var G = 1;
 
{
	var p1 = new THREE.Vector3(0,0,0);
	var p2 = new THREE.Vector3(1,0,0);
	var dis = p2.distanceTo(p1);
	
	var fparam =  G*2 / (dis*dis*dis);
	var force = new THREE.Vector3(0,0,0);
	force.add(p2.clone().sub(p1).multiplyScalar(fparam));;

	var a;
}


var frames = 0;
var lastFrams = 0;

var stats, options;

function timer(dur, worker){
	var timeDead = setTimeout("timer("+dur+","+worker+");", dur);
	worker();
}


function init(){
	scene = new THREE.Scene();

	camera = new THREE.PerspectiveCamera(70, window.innerWidth/window.innerHeight, 1, 1000);
	camera.position.z = 30;
	
	  renderer = new THREE.WebGLRenderer({
        canvas: document.getElementById('mainCanvas')
    });
	//renderer.setSize(window.innerWidth, window.innerHeight);
	//document.body.appendChild(renderer.domElement);

	{//option init
		var gui = new dat.GUI();
		options = {
			G: 1
		}
		gui.add(options, "G", 0.1, 32);
		//gui.domElement.style.position='absolute';
		gui.domElement.style.top = '8px';
		//document.getElementById('container').appendChild(gui.domElement);
	}

	{//stat init
		stats = new Stats();
		stats.domElement.style.position = 'absolute';
		stats.domElement.style.top = '8px';
		document.getElementById('container').appendChild(stats.domElement);
	}
	initCube();
	initBody();
}
function initCube(){
	var geometry = new THREE.CubeGeometry(1,1,1);
	var material = new THREE.MeshBasicMaterial({color: 0x00ff00});
	cube = new THREE.Mesh(geometry, material);
	cube.position.y = -10;
	cube.fresh = function(){
		cube.rotation.z += 0.1;
	}
	scene.add(cube);
}

function initBody(){
	bodys = new Array(
		new Body(5,  0 ,0, 0x0000ff, 2, 0,0,0),
		new Body(-5, 0, 0, 0x00ff00, 2, 0,0,0),
		new Body(-1, 5 ,0, 0xff0000, 1, 0,0,0)	
	);
}

function updateOption(){
	G = options.G;
}


function Body(x0,y0,z0, cor, mass, vx, vy, vz){
	this.viewObj= new THREE.Mesh(new THREE.SphereGeometry(0.3, 16,16), new THREE.MeshBasicMaterial({color:cor}));
	{this.viewObj.position.x = x0, this.viewObj.position.y = y0, this.viewObj.position.z = z0;scene.add(this.viewObj);};
	this.mass = mass;
	this.radius = 1;
	this.position = new THREE.Vector3(x0,y0,z0);
	this.velocity = new THREE.Vector3(vx, vy, vz);
	
	Body.prototype.calculateForce = function(bodies){
		this.force = new THREE.Vector3();
		for (var i=0; i<bodies.length; i++){
			var body = bodies[i];
			if (body==this){
				continue;
			}
			var dis = this.position.distanceTo(body.position);
			var fparam =  G*this.mass*body.mass / (dis*dis*dis);
			this.force.add(body.position.clone().sub(this.position).multiplyScalar(fparam));
		}
	};

	Body.prototype.nextPosition = function(time){
		this.velocity.add(this.force.divideScalar(this.mass).multiplyScalar(time));
		this.position.add(this.velocity.clone().multiplyScalar(time)); 
 		//this.viewObj.position = this.position;
		this.viewObj.position.x = this.position.x;
		this.viewObj.position.y = this.position.y;
		this.viewObj.position.z = this.position.z;
	}
}

function render() {
    requestAnimationFrame(render);
	
	for(var i=0; i<bodys.length; i++){
		var body = bodys[i];
		body.calculateForce(bodys);
	}

	for(var i=0; i<bodys.length; i++){
		var body = bodys[i];
		body.nextPosition(0.05);
	}
	cube.fresh();
	
	frames++;
	updateOption();
	stats.update();
    renderer.render(scene, camera);
}

init();
render();

