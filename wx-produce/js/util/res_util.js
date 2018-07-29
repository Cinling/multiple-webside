
export default class ResUrl {
  /**cnd根地址 */
  domain = "http://wxcdn.local/";
  imgTest = "img/test.png";

  /**获取全url路径 */
  Get(imgUrl) {
    return this.domain + imgUrl;
  }
}