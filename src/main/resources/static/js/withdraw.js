function deleteAccount() {
    if (confirm("정말 탈퇴하시겠습니까?")) {
        fetch('/users/delete', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
            .then(res => {
                if (res.ok) {
                    alert("탈퇴가 완료되었습니다.");
                    window.location.href = "/logout";
                } else {
                    alert("탈퇴 중 오류가 발생했습니다.");
                }
            });
    }
}