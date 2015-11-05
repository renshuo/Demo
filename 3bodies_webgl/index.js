
var scene = new THREE.Scene();

var renderer = new THREE.WebGLRenderer();

var camera = new THREE.PerspectiveCamera(40, window.innerWidth/window.innerHeight, 1, 1000);
	camera.position.z = 100;


var cube;

var bodys = Array(
	new Body(10,   0 ,0,  0x0000ff, 2, 0,0,0),
	new Body(-10, 0, 0, 0x00ff00, 2, 0,0,0),
	new Body(1,  10 ,0, 0xff0000, 1, 0,0,0)	
);

var G = 1;



function init(){
	renderer.setSize(window.innerWidth, window.innerHeight);
	document.body.appendChild(renderer.domElement);

	var geometry = new THREE.CubeGeometry(1,1,1);
	var material = new THREE.MeshBasicMaterial({color: 0x00ff00});
	cube = new THREE.Mesh(geometry, material);
	cube.position.y = -10;
	scene.add(cube);

}

function Position(x, y, z){
	this.x = x, this.y = y, this.z = z;
	Position.prototype.distance=function(pos1){
		var dx = pos1.x-this.x;
		var dy = pos1.y-this.y;
		var dz = pos1.z-this.z;
		return Math.sqrt(dx*dx+dy*dy+dz*dz);
	}
}

function Body(x0,y0,z0, cor, mass, vx, vy, vz){
	this.viewObj= new THREE.Mesh(new THREE.SphereGeometry(0.3, 16,16), new THREE.MeshBasicMaterial({color:cor}));
	{this.viewObj.position.x = x0, this.viewObj.position.y = y0, this.viewObj.position.z = z0;scene.add(this.viewObj);};
	this.mass = mass;
	this.radius = 1;
	this.position = new Position(x0, y0, z0);
	this.velocity = { x:vx, y:vy, z:vz	};
	this.force    = { x:0, y:0, z:0	};
	
	Body.prototype.calculateForce = function(bodies){
		this.force = {x:0, y:0, z:0};
		for (var i=0; i<bodies.length; i++){
			var body = bodies[i];
			if (body==this){
				continue;
			}
			var dis = this.position.distance(body.position);
			var fparam =  G*this.mass*body.mass / (dis*dis*dis);
			this.force.x += (body.position.x-this.position.x)*fparam;
			this.force.y += (body.position.y-this.position.y)*fparam;
			this.force.z += (body.position.z-this.position.z)*fparam;
		}
	};

	Body.prototype.nextPosition = function(time){
		this.velocity.x += this.force.x/this.mass*time;
		this.velocity.y += this.force.y/this.mass*time;
		this.velocity.z += this.force.z/this.mass*time;
		this.position.x += this.velocity.x*time;
		this.position.y += this.velocity.y*time;
		this.position.z += this.velocity.z*time;
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
		body.nextPosition(0.1);
	}
	
	cube.rotation.z += 0.1;

    renderer.render(scene, camera);
}
init();
render();


