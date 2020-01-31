/*
 * Copyright {2020} Baltnet Communications OÜ
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import React, {lazy, Suspense} from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter, Route} from "react-router-dom";
import Sidebar from "./Sidebar";

import './main.scss';

const HelloWorld = lazy(() => import('./HelloWorld'));

ReactDOM.render((
	<Suspense fallback={<h1>Loading...</h1>}>
		<BrowserRouter>
			<aside>
				<Sidebar/>
			</aside>
			<main>
				<Route exact path="/" component={HelloWorld}/>
			</main>
		</BrowserRouter>
	</Suspense>
), document.getElementById('app'));
