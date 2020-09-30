/**
 * 验证工具
 */

/**
 * 是否链接
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path);
}

// 手机号验证
export function isValidPhone(str) {
  const reg = /^1[3|4|5|7|8][0-9]\d{8}$/;
  return reg.test(str);
}
