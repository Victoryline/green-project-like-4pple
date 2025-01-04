$(function () {
    const chartElement = document.getElementById('chartData');
    if (!chartElement) {
        console.error("chartData element is not found!");
        return;
    }

    const ageData = JSON.parse(chartElement.getAttribute('data-age-json') || "[]");
    const genderData = JSON.parse(chartElement.getAttribute('data-gender-json') || "[]");

    const ageLabels = ['20대', '30대', '40대', '50대 이상'];
    const ageChartData = {
        labels: ageLabels,
        datasets: [{
            label: '나이대 분포',
            data: ageData,
            backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0']
        }]
    };

    const ageChartConfig = {
        type: 'pie',
        data: ageChartData,
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                tooltip: {
                    callbacks: {
                        label: function (context) {
                            const value = context.raw;
                            const total = context.dataset.data.reduce((acc, curr) => acc + curr, 0);
                            const percentage = ((value / total) * 100).toFixed(1);
                            return `${context.label}: ${value}명 (${percentage}%)`;
                        }
                    }
                }
            }
        }
    };

    new Chart(document.getElementById('ageChart'), ageChartConfig);

    const genderLabels = ['남자', '여자'];
    const genderChartData = {
        labels: genderLabels,
        datasets: [{
            label: '성별 비율',
            data: genderData,
            backgroundColor: ['#36A2EB', '#FF6384']
        }]
    };

    const genderChartConfig = {
        type: 'pie',
        data: genderChartData,
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                tooltip: {
                    callbacks: {
                        label: function (context) {
                            const value = context.raw;
                            const total = context.dataset.data.reduce((acc, curr) => acc + curr, 0);
                            const percentage = ((value / total) * 100).toFixed(1);
                            return `${context.label}: ${value}명 (${percentage}%)`;
                        }
                    }
                }
            }
        }
    };

    new Chart(document.getElementById('genderChart'), genderChartConfig);

    let previousValue = null;

    $(".applicant-status-select").on('focus', function () {
        previousValue = $(this).find(':selected').val();
    }).on('change', function (event) {
            const selectOption = $(this).find(':selected');
            const selectVal = selectOption.val();
            const selectTest = selectOption.text();

            if (selectVal === 'H') {
                alert(selectTest + "처리는 불가능합니다.");
                $(this).val(previousValue);
            } else {
                if (confirm("정말 " + selectTest + "처리 하시겠습니까?")) {
                    const jobPostNo = $('.applicant-status').data('job-post-no');
                    const resumeNo = this.getAttribute('data-resume-no');
                    api.put(`/api/v1/apply-resumes/${jobPostNo}?resumeNo=${resumeNo}&passYn=${encodeURIComponent(selectVal)}`)
                        .then(res => {
                            if (res.body == 1) {
                                alert(selectTest + '처리되었습니다.');
                                location.reload();
                            } else {
                                alert("처리 실패");
                                $(this).val(previousValue);
                            }
                        })
                        .catch(error => {
                            console.log(error);
                        })
                } else {
                    $(this).val(previousValue);
                }
            }
        }
    )
})


