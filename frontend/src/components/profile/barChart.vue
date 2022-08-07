<template>
  <div class="wrap-vertical">
    <Bar
      :chart-options="chartOptions"
      :chart-data="this.chart"
      :chart-id="chartId"
      :dataset-id-key="datasetIdKey"
      :plugins="plugins"
      :css-classes="cssClasses"
      :styles="styles"
      :height="height"
      :width="600"
    />
  </div>
</template>

<script>
import { Bar } from "vue-chartjs/legacy";
import { mapGetters } from "vuex";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
);

export default {
  name: "BarChart",
  components: {
    Bar,
  },
  computed: {
    ...mapGetters("analysis", ["chart"]),
  },
  props: {
    chartId: {
      type: String,
      default: "bar-chart",
    },
    datasetIdKey: {
      type: String,
      default: "label",
    },
    width: {
      type: Number,
      default: 400,
    },
    height: {
      type: Number,
      default: 400,
    },
    cssClasses: {
      default: "",
      type: String,
    },
    styles: {
      type: Object,
      default: () => {},
    },
    plugins: {
      type: Array,
      default: () => [],
    },
  },

  data() {
    return {
      chartOptions: {
        responsive: false,
        maintainAspectRatio: false,
      },
    };
  },
};
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: auto;
}
.wrap-vertical {
  width: 100%;
  padding: 0px;
  color: #112031;
  /* 가로 스크롤 */
  overflow: auto;
  white-space: nowrap;
}
</style>
