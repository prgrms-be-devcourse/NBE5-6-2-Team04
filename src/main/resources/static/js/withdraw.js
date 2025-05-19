function deleteAccount() {
    if (confirm("정말 탈퇴하시겠습니까?")) {
        const csrfToken = getCsrfToken();     // 메타 태그에서 가져오는 함수
        const csrfHeader = getCsrfHeader();

        fetch('/user/delete', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                [csrfHeader]: csrfToken
            }
        })
            .then(res => {
                if (res.ok) {
                    alert("탈퇴가 완료되었습니다.");
                    return fetch('/logout', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded',
                            [csrfHeader]: csrfToken
                        }
                    });
                } else {
                    alert("탈퇴 중 오류가 발생했습니다.");
                    throw new Error("탈퇴 실패");
                }
            })
            .then(() => {
                window.location.href = "/signin"; // 로그아웃 후 홈으로 이동
            })
            .catch(err => {
                console.error(err);
                alert("로그아웃 중 오류가 발생했습니다.");
            });
    }
}
function getCsrfToken() {
    const tokenMeta = document.querySelector('meta[name="_csrf"]');
    return tokenMeta ? tokenMeta.getAttribute('content') : '';
}

function getCsrfHeader() {
    const headerMeta = document.querySelector('meta[name="_csrf_header"]');
    return headerMeta ? headerMeta.getAttribute('content') : 'X-CSRF-TOKEN';
}