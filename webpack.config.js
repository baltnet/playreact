const path = require("path");

module.exports = {
	mode: "production",
	devtool: "source-map",

	entry: "./app/main/index.tsx",

	output: {
		path: path.resolve(__dirname, "public/javascripts"),
	},

	resolve: {
		extensions: [".ts", ".tsx", ".js", ".jsx"]
	},

	module: {
		rules: [
			{
				test: /\.ts(x?)$/,
				exclude: /node_modules/,
				use: ["ts-loader"]
			},
			// All output '.js' files will have any sourcemaps re-processed by 'source-map-loader'.
			{
				enforce: "pre",
				test: /\.js$/,
				loader: "source-map-loader"
			},
			{
				test: /\.s[ac]ss$/i,
				use: ["style-loader", "css-loader", "sass-loader"]
			}
		]
	},

	externals: {
		"react": "React",
		"react-dom": "ReactDOM",
		"prop-types": "PropTypes"
	}
};
