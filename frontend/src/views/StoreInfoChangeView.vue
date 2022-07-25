<template>
  <div>
    <h1>매장정보변경</h1>
    <!--이미지 변경 & 수정-->
    <img src="data:image/jpeg;base64,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" />
    <p>이미지 변경하는 아이콘</p>
  <div>

    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group class ="d-flex justify-content-between" id="input-group-1" label="매장명" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="form.store"
          type="text"
          placeholder="내 원래 매장명"
          required
        ></b-form-input>
      </b-form-group>
      <br>
      <b-form-group class ="d-flex justify-content-between" id="input-group-2" label="매장번호" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.phone"
          placeholder="원래 매장번호"
          required
        ></b-form-input>
      </b-form-group>
      <br>
      <b-form-group class ="d-flex justify-content-between" id="input-group-3" label="매장위치" label-for="input-3">
        <b-form-input
          id="input-3"
          v-model="form.location"
          placeholder="원래 매장위치"
          required
        ></b-form-input>
      </b-form-group>
      <br>
      <b-form-group class ="d-flex justify-content-between" id="input-group-4" label="사업자등록번호" label-for="input-4">
        <b-form-input
          id="input-4"
          v-model="form.registernum"
          placeholder="원래 사업자등록번호"
          required
        ></b-form-input>
      </b-form-group>
      <!--매장 종료시간-->
          <b-form-group class ="d-flex justify-content-between" id="input-group-5" label="매장 종료 시간" label-for="input-5">
          <b-form-timepicker v-model="value" locale="ko"></b-form-timepicker>
          </b-form-group>
      
      <!--매장 휴무일-->
      <b-form-group class ="d-flex justify-content-between" id="input-group-6" label="매장휴무일" label-for="input-6">
        <b-form-input
          id="input-6"
          v-model="form.holiday"
          placeholder="원래 매장 휴무일"
          required
        ></b-form-input>
        </b-form-group>
      <br>
      <div class="d-flex justify-content-between">
      <b-button type="reset" pill variant="outline-danger">초기화</b-button>
      <b-button type="submit" pill variant="outline-success">수정완료</b-button>
      </div>
    </b-form>
  </div>
  
  </div>
</template>

<script>
export default {
  name: "StoreInfoChangeView",
  data() {
      return {
        form: {
          store: '',
          phone: '',
          location: '',
          registernum: '',
          value: '',
          holiday:'',
        },
        show: true
      }
    },
    methods: {
      onSubmit(event) {
        event.preventDefault()
        alert("안녕하세요 변경한 사항을 저장하겠습니다"+JSON.stringify(this.form))
      },
      onReset(event) {
        event.preventDefault()
        // Reset our form values
        this.form.store = ''
        this.form.phone = ''
        this.form.location = ''
        this.form.registernum = ''
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.value = ''
        this.holiday= ''
        this.$nextTick(() => {
          this.show = true
        })
      }
    }

};
</script>

<style scoped></style>
