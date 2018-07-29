
import ResUtil from "./util/res_util.js"
import * as THREE from "./libs/three.min.js"

let ctx = canvas.getContext("webgl");
let resUtil = new ResUtil();

/**
 * 游戏主函数
 */
export default class Main {
  constructor() {
    this.Init();
  }

  // 初始化
  Init() {
    this.scene = new THREE.Scene();
    this.renderer = new THREE.WebGLRenderer({ context: canvas });
  }

  /**全屏变白 */
  FullWhite() {
    ctx.fillStyle = "#fff";
    ctx.fillRect(0, 0, canvas.width, canvas.height);
  }

  /**绘制图片 */
  DrawImg(src, x, y) {
    let image = wx.createImage();
    image.onload = function () {
      ctx.drawImage(image, 0, 0)
    };
    image.src = resUtil.Get(resUtil.imgTest);

    console.log(resUtil.Get(resUtil.imgTest));
  }
}
