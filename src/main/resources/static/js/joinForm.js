const id = document.getElementById("id");
const emailDomain = document.getElementById("emailDomain");
const domainSelect = document.getElementById("domainSelect");
const password1 = document.getElementById("password1");
const password2 = document.getElementById("password2");

const idFailMsg = document.querySelector(".idFailMsg");
const idFailMsg2 = document.querySelector(".idFailMsg2");
const idFailMsg3 = document.querySelector(".idFailMsg3");
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

// 아이디 6자 이상 18자 이하
function idLength(value) {
    return value.length >= 6 && value.length <= 18;
}

// 아이디 영어 또는 숫자만
function onlyNumberAndEnglish(str) {
    return /^[A-Za-z0-9]+$/.test(str);
}

// 이메일 형식 확인
function emailRegex(value) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value);
}

/* 도메인 선택값 */
function updateDomain() {
    let selectedDomain = domainSelect.value;
    emailDomain.value = selectedDomain;
}
// 비밀번호 8글자 이상 20자 이하, 영문, 숫자, 특수문자 사용
function strongPassword(str) {
    return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,20}$/.test(
        str
    );
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





// 아이디
    id.onkeyup = function () {
        if (id.value.length !== 0) {
            if (onlyNumberAndEnglish(id.value) === false) {
                idFailMsg.classList.remove("hide");
            } else if (idLength(id.value) === false) {
                idFailMsg.classList.add("hide");
                idFailMsg2.classList.remove("hide");
                idFailMsg3.classList.add("hide");
            } else {
                idFailMsg.classList.add("hide");
                idFailMsg2.classList.add("hide");
                idFailMsg3.classList.add("hide");
            }
        } else {
            idFailMsg.classList.add("hide");
            idFailMsg2.classList.add("hide");
            idFailMsg3.classList.add("hide");
        }
    };


// 비밀번호 유효성 검사
    password1.onkeyup = function () {
        if (password1.value.length !== 0) {
            if (strongPassword(password1.value)) {
                pwdFailMsg.classList.add("hide");
                pwdFailMsg2.classList.add("hide");
            } else {
                pwdFailMsg.classList.remove("hide");
                pwdFailMsg2.classList.add("hide");
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
            } else {
                pwdFailMsg2.classList.remove("hide");
                pwdFailMsg.classList.add("hide");
            }
        } else {
            pwdFailMsg.classList.add("hide");
            pwdFailMsg2.classList.add("hide");
        }
    };

// 이메일 형식 검사
    emailDomain.onkeyup = function () {
        let uEmail = id.value + "@" + emailDomain.value;
        if (emailDomain.value.length !== 0) {
            if (emailRegex(uEmail)) {
                console.log(emailRegex(uEmail));
                idFailMsg.classList.add("hide");
                idFailMsg2.classList.add("hide");
                idFailMsg3.classList.add("hide");
            } else {
                idFailMsg.classList.add("hide");
                idFailMsg2.classList.add("hide");
                idFailMsg3.classList.remove("hide");
            }
        } else {
            idFailMsg.classList.add("hide");
            idFailMsg2.classList.add("hide");
            idFailMsg3.classList.add("hide");
        }
    };

// 이름  유효성 검사
uName.onkeyup = function () {
    if (uName.value.length !== 0) {
        if (validateUName(uName.value)) {
            uNameFailMsg.classList.add("hide");
        } else {
            uNameFailMsg.classList.remove("hide");
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
        } else {
            nickNameFailMsg.classList.remove("hide");
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
        } else {
            phoneFailMsg.classList.remove("hide");
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
        } else {
            birthFailMsg.classList.remove("hide");
        }
    } else {
        birthFailMsg.classList.add("hide");
    }
};


