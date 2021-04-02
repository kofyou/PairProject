export function validateAccountNumber(str) {
  const reg = /^[\u4E00-\u9FA5A-Za-z0-9]{2,20}$/;
  return reg.test(str)
}
export function validatePasswordNumber(str) {
  const reg = /^[a-zA-Z]\w{5,17}$/;
  return reg.test(str)
}
