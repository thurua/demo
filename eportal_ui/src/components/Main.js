import React from 'react';
import { Switch, Route } from 'react-router-dom';

import Cong from './Cong';
import Toan from './Toan';
import Hoan from './Hoan';

const Main = () => (
    <main>
        <Switch>
            <Route exact path="/cong" component={Cong} />
            <Route path="/toan" component={Toan} />
            <Route path="/hoan" component={Hoan} />
        </Switch>
    </main>
)

export default Main