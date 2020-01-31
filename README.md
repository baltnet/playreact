# About This Template
This is a sample blueprint for [React](https://reactjs.org) frontend that use [Play Framework](https://www.playframework.com) for the backend tasks. Many similar projects put frontend code into a separate subdirectory that utilizes typical React environment including `reac-scripts` helper etc. This is not the case here. This template contains single build environment where all the source code resides in `app` folder.

## Benefits
There are various benefits when using such a layout:
* Allows full-stack developers to implement functionality in a modular basis where every subfolder inside the `app` directory houses both code for the backend server and corresponding user interface.
* Provides modular approach that limits functional behavior with a single subfolder in a build tree.
* All maintainer and helper scripts are at in the root folder of the build tree. Other similar projects put NodeJS and React related scripts to the subfolder creating a scattered build environment.
* Play Framework hooks and SBT build scripts are used to invoke `webpack` and make it watch the changes in user interface files.
* Fully integrated with the SBT build system.
* Output is a single set of files with frontend scripts packed in a separate JAR-file.
* User interface files are served as static assets straight from Netty server bundled to Play framework. No additional servers or configurations are needed.

## Drawbacks
* Frontend and backend is tightly coupled. It is not an easy work to convert them to separate projects.
* Single server instance handles all the load; first serving user interface files, then accepting API requests initiated by the user interface.
* Using load balancing is complicating, the easiest way would be spawn another instance of the server. It might be possible to configure some instances to support only API or user interface download requests.

# Components
* [Play Framework](https://www.playframework.com)
* [Webjars](https://www.webjars.org)
* [SBT build environment](https://www.scala-sbt.org) (required by Play)
* [React](https://reactjs.org)
* [React Router](https://reacttraining.com/react-router/core/guides/philosophy)
* [Typescript](https://www.typescriptlang.org)
* [Webpack](https://webpack.js.org)
* [Node-SASS](https://github.com/sass/node-sass)

Although [NodeJS](https://nodejs.org/en/) package manager [NPM](https://www.npmjs.com) is used for managing all JavaScript dependencies, NodeJS itself is not required as development is done using a Play Framework server instance for both backend and user interface.

# TODO
* Add support for triggering refresh in browser when user interface code changes. Most probably requires websocket connection and some helper scripts (to be investigated).
* Add preliminary packaging support (Dockerfile, Debian archive).

# License
All code in this repository is published under Apache 2.0 license.

# Credits
External work used in this project:
* [Webpack.scala](https://github.com/nouhoum/play-react-webpack/blob/master/project/Webpack.scala) by nouhoum to hook Webpack commands with Play Framework.
