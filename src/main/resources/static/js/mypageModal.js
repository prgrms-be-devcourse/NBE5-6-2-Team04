function openModal() {
    document.getElementById("achievementModal").style.display = "block";

    const list = document.getElementById("achievementList");
    list.innerHTML = `
       <div class="achievement-item"><b>Hello, World!</b><span> 첫 할 일을 완료했습니다.</span></div>
        <div class="achievement-item"><b>진로 탐색가</b><span> 관심 직무나 기업을 설정했습니다.</span></div>
        <div class="achievement-item"><b>환경 세팅 완료</b><span> 개발 환경을 모두 세팅했습니다.</span></div>
        <div class="achievement-item"><b>이론 마스터</b><span> CS 기본 개념 10개를 학습했습니다.</span></div>
        <div class="achievement-item"><b>프로젝트 헌터</b><span> 프로젝트를 1개 이상 등록했습니다.</span></div>
        <div class="achievement-item"><b>알고리즘 도전자</b><span> 알고리즘 문제 10개 이상을 풀었습니다.</span></div>
        <div class="achievement-item"><b>나만의 포트폴리오</b><span> GitHub에 프로젝트를 업로드했습니다.</span></div>
        <div class="achievement-item"><b>면접 시뮬레이터</b><span> 모의 면접을 수행했습니다.</span></div>
        <div class="achievement-item"><b>지원 발사!</b><span> 지원할 회사를 등록했습니다.</span></div>
        <div class="achievement-item"><b>멘탈 관리 마스터</b><span> 7일 연속 할 일을 완료했습니다.</span></div>
  `;
}

function closeModal() {
    document.getElementById("achievementModal").style.display = "none";
}
