const path = require("path");
const UglifyJsPlugin = require("uglifyjs-webpack-plugin"); // 去掉注释
const CompressionWebpackPlugin = require("compression-webpack-plugin"); // 开启压缩
const { HashedModuleIdsPlugin } = require("webpack");

function resolve(dir) {
  return path.join(__dirname, dir);
}

const isProduction = process.env.NODE_ENV === "production";

// cdn预加载使用
const externals = {
  vue: "Vue",
  "vue-router": "VueRouter",
  vuex: "Vuex",
  axios: "axios",
  "element-ui": "ELEMENT"
};

const cdn = {
  // 开发环境
  dev: {
    css: ["https://unpkg.com/element-ui/lib/theme-chalk/index.css"],
    js: []
  },
  // 生产环境,版本参考package.json
  build: {
    css: ["https://unpkg.com/element-ui/lib/theme-chalk/index.css"],
    js: [
      "https://cdn.jsdelivr.net/npm/vue@2.6.11/dist/vue.min.js",
      "https://cdn.jsdelivr.net/npm/vue-router@3.2.0/dist/vue-router.min.js",
      "https://cdn.jsdelivr.net/npm/vuex@3.4.0/dist/vuex.min.js",
      "https://cdn.jsdelivr.net/npm/axios@0.19.2/dist/axios.min.js",
      "https://unpkg.com/element-ui/lib/index.js"
    ]
  }
};

module.exports = {
  lintOnSave: false, // 关闭eslint
  productionSourceMap: false,
  publicPath: "./",
  outputDir: process.env.outputDir, // 生成文件的目录名称，默认dist
  css: {
    loaderOptions: {
      scss: {
        // 引入全局scss文件
        prependData: `@import "@/styles/index.scss";`
      }
    }
  },
  chainWebpack: config => {
    config.resolve.alias // 文件指向
      .set("@", resolve("src"))
      .set("api", resolve("src/api"))
      .set("assets", resolve("src/assets"))
      .set("components", resolve("src/components"))
      .set("views", resolve("src/views"));

    // 压缩图片
    // config.module
    //   .rule("images")
    //   .test(/\.(png|jpe?g|gif|svg)(\?.*)?$/)
    //   .use("image-webpack-loader")
    //   .loader("image-webpack-loader")
    //   .options({ bypassOnDebug: true });

    // webpack 会默认给commonChunk打进chunk-vendors，所以需要对webpack的配置进行delete
    config.optimization.delete("splitChunks");

    config.plugin("html").tap(args => {
      if (process.env.NODE_ENV === "production") {
        args[0].cdn = cdn.build;
      }
      if (process.env.NODE_ENV === "development") {
        args[0].cdn = cdn.dev;
      }
      return args;
    });

    // 包分析
    // config
    //   .plugin("webpack-bundle-analyzer")
    //   .use(require("webpack-bundle-analyzer").BundleAnalyzerPlugin);
  },

  configureWebpack: config => {
    const plugins = [];

    if (isProduction) {
      plugins.push(
        new UglifyJsPlugin({
          uglifyOptions: {
            output: {
              comments: false // 去掉注释
            },
            warnings: false,
            compress: {
              drop_console: true,
              drop_debugger: false,
              pure_funcs: ["console.log"] //移除console
            }
          }
        })
      );
      // 服务器也要相应开启gzip
      // 开启Gzip压缩，包含文件js、css
      plugins.push(
        new CompressionWebpackPlugin({
          algorithm: "gzip",
          test: /\.(js|css)$/, // 匹配文件名
          threshold: 10000, // 对超过10k的数据压缩
          deleteOriginalAssets: false, // 不删除源文件
          minRatio: 0.8 // 压缩比
        })
      );

      // 用于根据模块的相对路径生成 hash 作为模块 id, 一般用于生产环境
      plugins.push(new HashedModuleIdsPlugin());

      // 开启分离js
      config.optimization = {
        runtimeChunk: "single",
        splitChunks: {
          chunks: "all",
          maxInitialRequests: Infinity,
          minSize: 1000 * 60,
          cacheGroups: {
            vendor: {
              test: /[\\/]node_modules[\\/]/,
              name(module) {
                // 排除node_modules 然后吧 @ 替换为空 ,考虑到服务器的兼容
                const packageName = module.context.match(
                  /[\\/]node_modules[\\/](.*?)([\\/]|$)/
                )[1];
                return `npm.${packageName.replace("@", "")}`;
              }
            }
          }
        }
      };

      // 取消webpack警告的性能提示
      config.performance = {
        hints: "warning",
        //入口起点的最大体积
        maxEntrypointSize: 1000 * 500,
        //生成文件的最大体积
        maxAssetSize: 1000 * 1000,
        //只给出 js 文件的性能提示
        assetFilter: function(assetFilename) {
          return assetFilename.endsWith(".js");
        }
      };

      // 打包时npm包转CDN
      config.externals = externals;
    }

    return { plugins };
  },

  devServer: {
    // 本地代理
    open: false, // 自动启动浏览器
    host: "0.0.0.0", // localhost
    port: 9527, // 端口号
    https: false,
    hotOnly: false, // 热更新
    proxy: {
      "/api": {
        target: process.env.VUE_APP_BASE_API, // 重写路径
        ws: true, //开启WebSocket
        secure: false, // 如果是https接口，需要配置这个参数
        changeOrigin: true,
        pathRewrite: {
          "^/api": ""
        }
      }
    }
  }
};
