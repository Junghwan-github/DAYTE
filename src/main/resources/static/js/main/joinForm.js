let pwd1Chk = false;
let pwd2Chk = false;
let uNameChk = false;
let nickNameChk = false;
let phoneChk = false;
let birthChk = false;

const id = document.getElementById("id");
const emailDomain = document.getElementById("emailDomain");
const domainSelect = document.getElementById("domainSelect");
const password1 = document.getElementById("password1");
const password2 = document.getElementById("password2");

const pwdFailMsg = document.querySelector(".pwdFailMsg");
const pwdFailMsg2 = document.querySelector(".pwdFailMsg2");

const uName = document.getElementById("userName");
const nickName = document.getElementById("nickName");
const phone = document.getElementById("phone");
const birthDate = document.getElementById("birthDate");

const uNameFailMsg = document.querySelector(".uNameFailMsg");
const nickNameFailMsg = document.querySelector(".nickNameFailMsg");
const phoneFailMsg = document.querySelector(".phoneFailMsg");
const birthFailMsg = document.querySelector(".birthFailMsg");

/* 도메인 선택값 */
function updateDomain() {
    let selectedDomain = domainSelect.value;
    emailDomain.value = selectedDomain;
}
// 비밀번호 8글자 이상 20자 이하, 영문, 숫자, 특수문자 사용
function strongPassword(str) {
    return /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*?_]).{8,20}$/.test(str);

}

// 비밀번호 확인
function isMatch(password1, password2) {
    return password1 === password2;
}

// 이름 형식 확인 ( 한글, 영어 대소문자만 가능 특수기호,공백 불가 )
function validateUName(value) {
    return /^[가-힣a-zA-Z]+$/.test(value);
}

// 닉네임 형식 확인 ( 2 ~ 10글자 한글, 숫자, 영어 대소문자만 가능 )
function validateNickName(value) {
    return /^[가-힣a-zA-Z0-9]{2,10}$/.test(value);
}

/* 휴대전화 번호 형식 확인 
  010, 011, 016, 017, 018, 019 중 하나로 시작
  하이픈(-)이 0 또는 1회 등장할 수 있음
*/
function validatePhone(value) {
    return /^(01[016789])-?([0-9]{3,4})-?([0-9]{4})$/.test(value);
}

// 생년월일 형식 확인
function validateBirth(value) {
    return /^(19|20)\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])$/.test(value);
}

// 비밀번호 유효성 검사
    password1.onkeyup = function () {
        if (password1.value.length !== 0) {
            if (strongPassword(password1.value)) {
                pwdFailMsg.classList.add("hide");
                pwdFailMsg2.classList.add("hide");
                pwd1Chk = true;
            } else {
                pwdFailMsg.classList.remove("hide");
                pwdFailMsg2.classList.add("hide");
                pwd1Chk = false;
            }
        } else {
            pwdFailMsg.classList.add("hide");
            pwdFailMsg2.classList.add("hide");
        }
    };

// 비밀번호 일치 검사
    password2.onkeyup = function () {
        if (password2.value.length !== 0) {
            if (isMatch(password1.value, password2.value)) {
                pwdFailMsg.classList.add("hide");
                pwdFailMsg2.classList.add("hide");
                pwd2Chk = true;
            } else {
                pwdFailMsg2.classList.remove("hide");
                pwdFailMsg.classList.add("hide");
                pwd2Chk = false;
            }
        } else {
            pwdFailMsg.classList.add("hide");
            pwdFailMsg2.classList.add("hide");
        }
    };

// 이름  유효성 검사
uName.onkeyup = function () {
    if (uName.value.length !== 0) {
        if (validateUName(uName.value)) {
            uNameFailMsg.classList.add("hide");
            uNameChk = true;
        } else {
            uNameFailMsg.classList.remove("hide");
            uNameChk = false;
        }
    } else {
        uNameFailMsg.classList.add("hide");
    }
};

// 닉네임 유효성 검사
nickName.onkeyup = function () {
    if (nickName.value.length !== 0) {
        if (validateNickName(nickName.value)) {
            nickNameFailMsg.classList.add("hide");
            nickNameChk = true;
        } else {
            nickNameFailMsg.classList.remove("hide");
            nickNameChk = false;
        }
    } else {
        nickNameFailMsg.classList.add("hide");
    }
};

// 휴대전화번호 형식 검사
phone.onkeyup = function () {
    if (phone.value.length !== 0) {
        if (validatePhone(phone.value)) {
            phoneFailMsg.classList.add("hide");
            phoneChk = true;
        } else {
            phoneFailMsg.classList.remove("hide");
            phoneChk = false;
        }
    } else {
        phoneFailMsg.classList.add("hide");
    }
};

// 생년월일 형식 검사
birthDate.onkeyup = function () {
    if (birthDate.value.length !== 0) {
        if (validateBirth(birthDate.value)) {
            birthFailMsg.classList.add("hide");
            birthChk = true;
        } else {
            birthFailMsg.classList.remove("hide");
            birthChk = false;
        }
    } else {
        birthFailMsg.classList.add("hide");
    }
};
