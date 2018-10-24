import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';

import moment from '@material-ui/core/TextField';
// import FormControl from '@material-ui/core/FormControl';
// import MenuItem from '@material-ui/core/MenuItem';
// import Select from '@material-ui/core/Select';
// import InputLabel from '@material-ui/core/InputLabel';

const styles = theme => ({
	container: {
		display: 'flex',
		flexWrap: 'wrap',
	},
	textField: {
		marginLeft: theme.spacing.unit,
		marginRight: theme.spacing.unit,
		width: 200,
	},
});

class Schedule extends React.Component {
	render() {
		const { classes } = this.props;
		return (	
			<form className={classes.container} noValidate>
				<div>
					<div>
						<div>
							<label>
								Client
						</label>
							<label style={{ paddingLeft: "10px" }}>Toan</label>
							<label style={{ paddingLeft: "280px" }}>
								Client Account
						</label>
							<label style={{ paddingLeft: "10px" }}>Toan</label>
						</div>
					</div>
					<div>
						<div>
							<label>
								Created Date From
						</label>
							<TextField
								id="date"
								type="date"
								defaultValue="2017-05-24"
								formatDate={(date) => moment(date).format('dd-MMM-yyyy')}
								className={classes.textField}
								InputLabelProps={{
									shrink: true,
								}}
							/>
							<label style={{ paddingLeft: "15px" }}>
								Created Date To
						</label>
							<TextField
								id="date"
								type="date"
								defaultValue="2017-05-24"
								formatDate={(date) => moment(date).format('DD-MM-YYYY')}
								className={classes.textField}
								InputLabelProps={{
									shrink: true,
								}}
							/>
						</div>
					</div>
					<div>
					</div>
				</div>
			</form>	
		);
	}
}

Schedule.propTypes = {
	classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Schedule);