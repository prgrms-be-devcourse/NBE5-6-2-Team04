function goToDashboard() {
    location.href = '/dashboard';
}

function logout() {
    fetch('/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-CSRF-TOKEN': getCsrfToken()
        }
    }).then(() => {
        window.location.href = '/';
    }).catch(err => {
        console.error('Logout failed', err);
        alert('로그아웃 실패');
    });
}

function getCsrfToken() {
    const token = document.querySelector('meta[name="_csrf"]');
    return token ? token.getAttribute('content') : '';
}