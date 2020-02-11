<template>
  <div class="hello">
    <p>{{freq}}</p>
    <div ref="cav1" id="canvas1"></div>
  </div>
</template>

<script>
import * as three from 'three'

let scene = null
let camera = null
let renderer = null

let objs = []
let i = 0

function generateLine(size, color, y0, height) {
  let geometry = new three.BufferGeometry()
  let positions = []

  let max = size

  for (let i=0; i<max; i++){
    var x = i/max*2000-1000
		var y = Math.random()*height - height/2 + y0
		var z = 0;
    positions.push(x, y, z)
  }

  geometry.setAttribute('position', new three.Float32BufferAttribute(positions, 3))
  geometry.computeBoundingSphere()
  let material = new three.LineBasicMaterial({color})
  let mesh = new three.Line(geometry, material)
  mesh.userData = {
    y0, height
  }
  objs.push(mesh)
  scene.add(mesh)
}

function init(container) {
  console.log('init threejs')
  scene = new three.Scene()
  camera = new three.PerspectiveCamera(27, 1.5, 5, 3500)
  camera.position.z = 3000
  scene.add(camera)

  let size = 60000
  generateLine(size, 0x00ff00, 0, 90)
  generateLine(size, 0xff0000, 100, 90)
  generateLine(size, 0x0000ff, -100, 90)
  
  renderer = new three.WebGLRenderer()
  renderer.setSize(800, 600)
  container.appendChild(renderer.domElement)
}

function ani () {
  requestAnimationFrame(ani)
  for(let i=0; i<objs.length; i++){
    let line = objs[i]
    let ps = line.geometry.getAttribute('position').array
    for(let j=0; j<ps.length/3; j++) {
      let y = j*3+1
      ps[y] =  Math.random()*line.userData.height - line.userData.height/2 + line.userData.y0
    }
    line.geometry.setAttribute('position', new three.Float32BufferAttribute(ps, 3))
    line.geometry.computeBoundingSphere()
  }
  i++
  renderer.render(scene, camera)
}


export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data (){
    return {
      lasti: 0,
      freq: 0
  }},
  methods: {
  },
  mounted () {
    init(this.$refs.cav1)
    ani()
    this.timer = setInterval(()=>{
      this.freq = i - this.lasti
      this.lasti = i
    }, 1000)
  }
}
</script>

<style>
</style>
