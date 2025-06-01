$(document).ready(function () {
    loadProvinces();

    $('#province').change(function () {
        const provinceId = $(this).val();
        $('#district').prop('disabled', false);
        $.getJSON('/location?action=district&province_id=' + provinceId, function (data) {
            const districts = data.data;
            $('#district').empty().append('<option value="">Chọn quận/huyện</option>');
            districts.forEach(d => {
                $('#district').append('<option value="' + d.DistrictID + '">' + d.DistrictName + '</option>');
            });
        });

    });

    $('#district').change(function () {
        const districtId = $(this).val();
        $('#ward').prop('disabled', false);
        $.getJSON('/location?action=ward&district_id=' + districtId, function (data) {
            const wards = data.data;
            $('#ward').empty().append('<option value="">Chọn phường/xã</option>');
            wards.forEach(w => {
                $('#ward').append('<option value="' + w.WardCode + '">' + w.WardName + '</option>');
            });
        });
    });


    function loadProvinces() {
        $.getJSON('/location?action=province', function (data) {
            const provinces = data.data;
            $('#province').empty().append('<option value="">Chọn tỉnh/thành phố</option>');
            provinces.forEach(p => {
                $('#province').append('<option value="' + p.ProvinceID + '">' + p.ProvinceName + '</option>');
            });
        });
    }

    function syncSelectedNames() {
        $('#provinceName').val($('#province option:selected').text());
        $('#districtName').val($('#district option:selected').text());
        $('#wardName').val($('#ward option:selected').text());
    }

    $('#province, #district, #ward').change(syncSelectedNames);
    $(document).on('click', '#createAddress button[type=submit]', function () {
        $('#provinceName').val($('#province option:selected').text());
        $('#districtName').val($('#district option:selected').text());
        $('#wardName').val($('#ward option:selected').text());

        console.log('Button clicked - hidden fields populated');
    });
});

